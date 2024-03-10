package ru.nsu.burde.crackhash.common;

public class TaskForWorkerDTO {
    private String requestId;
    private int partNumber;
    private int partCount;
    private String hash;
    private int maxLength;
    private String[] alphabet;

    public TaskForWorkerDTO(){
    }
    public TaskForWorkerDTO(String requestId, int partNumber, int partCount, String hash, int maxLength, String[] alphabet) {
        this.requestId = requestId;
        this.partNumber = partNumber;
        this.partCount = partCount;
        this.hash = hash;
        this.maxLength = maxLength;
        this.alphabet = alphabet;
    }

    public static TaskForWorkerDTOBuilder builder() {
        return new TaskForWorkerDTOBuilder();
    }

    public String getRequestId() {
        return this.requestId;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public int getPartCount() {
        return this.partCount;
    }

    public String getHash() {
        return this.hash;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public String[] getAlphabet() {
        return this.alphabet;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public void setPartCount(int partCount) {
        this.partCount = partCount;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setAlphabet(String[] alphabet) {
        this.alphabet = alphabet;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TaskForWorkerDTO)) return false;
        final TaskForWorkerDTO other = (TaskForWorkerDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$requestId = this.getRequestId();
        final Object other$requestId = other.getRequestId();
        if (this$requestId == null ? other$requestId != null : !this$requestId.equals(other$requestId)) return false;
        if (this.getPartNumber() != other.getPartNumber()) return false;
        if (this.getPartCount() != other.getPartCount()) return false;
        final Object this$hash = this.getHash();
        final Object other$hash = other.getHash();
        if (this$hash == null ? other$hash != null : !this$hash.equals(other$hash)) return false;
        if (this.getMaxLength() != other.getMaxLength()) return false;
        if (!java.util.Arrays.deepEquals(this.getAlphabet(), other.getAlphabet())) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TaskForWorkerDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $requestId = this.getRequestId();
        result = result * PRIME + ($requestId == null ? 43 : $requestId.hashCode());
        result = result * PRIME + this.getPartNumber();
        result = result * PRIME + this.getPartCount();
        final Object $hash = this.getHash();
        result = result * PRIME + ($hash == null ? 43 : $hash.hashCode());
        result = result * PRIME + this.getMaxLength();
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getAlphabet());
        return result;
    }

    public String toString() {
        return "TaskForWorkerDTO(requestId=" + this.getRequestId() + ", partNumber=" + this.getPartNumber() + ", partCount=" + this.getPartCount() + ", hash=" + this.getHash() + ", maxLength=" + this.getMaxLength() + ", alphabet=" + java.util.Arrays.deepToString(this.getAlphabet()) + ")";
    }

    public static class TaskForWorkerDTOBuilder {
        private String requestId;
        private int partNumber;
        private int partCount;
        private String hash;
        private int maxLength;
        private String[] alphabet;

        TaskForWorkerDTOBuilder() {
        }

        public TaskForWorkerDTOBuilder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public TaskForWorkerDTOBuilder partNumber(int partNumber) {
            this.partNumber = partNumber;
            return this;
        }

        public TaskForWorkerDTOBuilder partCount(int partCount) {
            this.partCount = partCount;
            return this;
        }

        public TaskForWorkerDTOBuilder hash(String hash) {
            this.hash = hash;
            return this;
        }

        public TaskForWorkerDTOBuilder maxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public TaskForWorkerDTOBuilder alphabet(String[] alphabet) {
            this.alphabet = alphabet;
            return this;
        }

        public TaskForWorkerDTO build() {
            return new TaskForWorkerDTO(this.requestId, this.partNumber, this.partCount, this.hash, this.maxLength, this.alphabet);
        }

        public String toString() {
            return "TaskForWorkerDTO.TaskForWorkerDTOBuilder(requestId=" + this.requestId + ", partNumber=" + this.partNumber + ", partCount=" + this.partCount + ", hash=" + this.hash + ", maxLength=" + this.maxLength + ", alphabet=" + java.util.Arrays.deepToString(this.alphabet) + ")";
        }
    }
}
